package com.swg.mvvmdemo.ui;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.swg.mvvmdemo.R;
import com.swg.mvvmdemo.databinding.ActivityMainBinding;
import com.swg.mvvmdemo.viewmode.ExpressViewModel;

/**
 * Created by swg on 2017/11/10.
 */

public class MainActivity extends BaseActivity {

    private ProgressDialog progressDialog;
    private ExpressViewModel expressViewModel;

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        expressViewModel = new ExpressViewModel(this, mainBinding);
        mainBinding.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressViewModel.getExpressInfo("youzheng", "9891496112599");
            }
        });
        // 显示Loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在获取快递信息...");

        expressViewModel.isShowLoading.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (expressViewModel.isShowLoading.get()) {
                    progressDialog.show();
                } else {
                    progressDialog.dismiss();
                }
            }
        });
        // 显示错误信息
        expressViewModel.errorMessage.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Toast.makeText(MainActivity.this, expressViewModel.errorMessage.get(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
