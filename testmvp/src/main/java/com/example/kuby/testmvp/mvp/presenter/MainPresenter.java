package com.example.kuby.testmvp.mvp.presenter;

import com.example.kuby.testmvp.mvp.model.IMainMode;
import com.example.kuby.testmvp.mvp.model.MainMode;
import com.example.kuby.testmvp.mvp.view.IMainView;

/**
 * Created by Administrator on 2016/11/6.
 */
public class MainPresenter implements IMainPresenter {
    IMainView iMainView;
    IMainMode iMainMode;

    public MainPresenter() {
        iMainMode = new MainMode();
    }

    @Override
    public void attachView(IMainView view) {
        this.iMainView = view;
    }

    @Override
    public void detachView() {
        this.iMainView = null;
    }

    @Override
    public void searchUser(String loginName) {
        iMainMode.getUser(loginName);
    }
}
