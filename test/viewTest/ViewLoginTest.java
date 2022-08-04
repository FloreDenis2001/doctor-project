package viewTest;

import mycode.view.ViewLogin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewLoginTest {

    @Test
    public void viewTest(){
        ViewLogin viewLogin=new ViewLogin();
        viewLogin.play();
    }

}