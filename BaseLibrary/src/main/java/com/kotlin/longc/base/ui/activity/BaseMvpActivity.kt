package com.kotlin.longc.base.ui.activity

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import com.kotlin.longc.base.common.BaseApplication
import com.kotlin.longc.base.injection.component.ActivityComponent
import com.kotlin.longc.base.injection.component.DaggerActivityComponent
import com.kotlin.longc.base.injection.module.ActivityModule
import com.kotlin.longc.base.injection.module.LifecycleProviderModule
import com.kotlin.longc.base.presenter.BasePresenter
import com.kotlin.longc.base.presenter.view.BaseView
import javax.inject.Inject

/**
 * Created by mac on 2019/1/7.
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
    }

    protected abstract fun injectComponent()

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}