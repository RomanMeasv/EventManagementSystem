package ems.bll;

import ems.dal.MockData;

public class LoginPageLogic {

    MockData mockData;

    public LoginPageLogic() {
        mockData = new MockData();
    }

    public boolean tryLogin(String username, String password) {
        return mockData.tryLogin(username, password);
    }
}
