package com.jiyun.geeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jiyun.geeknews.R;
import com.jiyun.geeknews.base.BaseActivity;
import com.jiyun.geeknews.presenter.EmptyPresenter;
import com.jiyun.geeknews.utils.DateUtil;
import com.jiyun.geeknews.view.EmptyView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    private static final String TAG = "CalendarActivity";
    @BindView(R.id.calendarView)
    MaterialCalendarView calendarView;
    CalendarDay mDate;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initListener() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setTitle("选择日期");


        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2018, 5, 20))
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate = date;
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rutrnZhihui();
            }
        });
    }

    private void rutrnZhihui() {
        Date date = mDate.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy--MM--dd");
        String format1 = format.format(date);
        char[] chars = format1.toCharArray();
        String str = "";
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                str+=chars[i];
            }
        }
        Log.d(TAG, "rutrnZhihui: "+str);
        Intent intent = getIntent();
        intent.putExtra("date",str);
        setResult(20,intent);
        finish();
    }

    //设置监听事件,点击返回按钮则退出当前页面
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void initView() {
        calendarView.getCurrentDate();
    }


}
