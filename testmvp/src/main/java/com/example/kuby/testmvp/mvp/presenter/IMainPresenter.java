package com.example.kuby.testmvp.mvp.presenter;

import com.example.kuby.testmvp.mvp.view.IMainView;

/**
 * Created by Administrator on 2016/11/6.
 */
public interface IMainPresenter<T extends IMainView> {
    void attachView(T view);
    void detachView();
    void searchUser(String loginName);
}
