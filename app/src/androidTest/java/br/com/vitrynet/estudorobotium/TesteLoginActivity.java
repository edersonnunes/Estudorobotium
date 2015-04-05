package br.com.vitrynet.estudorobotium;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import com.robotium.solo.Solo;

/**
 * Created by Ederson on 01/04/2015.
 */
public class TesteLoginActivity
        extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Activity activity;
    private Solo solo;

    public TesteLoginActivity() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        solo = new Solo(getInstrumentation(), activity);
    }

    public void testaValidacoesLoginActivity() {

        try {
            EditText editEmail = (EditText) activity.findViewById(R.id.email);

            solo.enterText(editEmail, "zezinho@testlandia.com");
            solo.clickOnButton("Autenticar");
            solo.takeScreenshot();

            boolean achouTextoDeErro = solo.waitForText(activity.getString(R.string.erro_senha_incorreta), 1, 5000);

            if(achouTextoDeErro) {
                assertTrue(true);
            }
            else {
                assertTrue(false);
            }
        }catch (Exception e) {
            //Se ocorrer exceção teste também falhou!
            assertTrue(false);
        }
    }

    public void testaDirecionamentoDeFluxoAutenticado() {

        try {
            EditText editEmail = (EditText) activity.findViewById(R.id.email);
            EditText editSenha = (EditText) activity.findViewById(R.id.password);

            solo.enterText(editEmail, "zezinho@testlandia.com");
            solo.enterText(editSenha, "teste");
            solo.takeScreenshot();
            solo.clickOnButton("Autenticar");

            boolean achouActivity = solo.waitForActivity(UsuarioAutenticadoActivity.class, 5000);

            if(achouActivity) {
                assertTrue(true);
            }
            else {
                assertTrue(false);
            }
        }catch (Exception e) {
            //Se ocorrer exceção teste também falhou!
            assertTrue(false);
        }
    }

    @Override
    protected void tearDown() throws Exception {

        solo.finishOpenedActivities();
        super.tearDown();
    }
}
