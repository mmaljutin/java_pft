package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        String newPassword = "newpass";
        app.getNavigationHelper().goToLoginPage();
        app.getUserHelper().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.getNavigationHelper().goToManageUserPage();
        UserData changedUser = app.getUserHelper().getUserFromBD();
        app.getNavigationHelper().goToUserPage(changedUser.getId());
        app.getUserHelper().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, changedUser.getEmail());
        app.registration().finish(confirmationLink, newPassword);
        HttpSession sessionUser = app.newSession();
        assertTrue(sessionUser.login(changedUser.getUsername(), newPassword));
        assertTrue(sessionUser.isLoggedInAs(changedUser.getUsername()));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
