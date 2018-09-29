package es.iessaladillo.maria.exchange;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private EditText lblAmount;
    private RadioGroup rdgFromCurrency;
    private RadioGroup rdgToCurrency;
    private RadioButton rbtEuro01;
    private RadioButton rbtEuro02;
    private RadioButton rbtDollar01;
    private RadioButton rbtDollar02;
    private RadioButton rbtYen01;
    private RadioButton rbtYen02;
    private ImageView imgFromCurrency;
    private ImageView imgToCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        lblAmount = ActivityCompat.requireViewById(this, R.id.txtAmount);
        rdgFromCurrency = ActivityCompat.requireViewById(this, R.id.rdgFromCurrency);
        rdgToCurrency= ActivityCompat.requireViewById(this, R.id.rdgToCurrency);
        rbtEuro01 = ActivityCompat.requireViewById(this, R.id.rbtEuro01);
        rbtEuro02 = ActivityCompat.requireViewById(this, R.id.rbtEuro02);
        rbtDollar01 = ActivityCompat.requireViewById(this, R.id.rbtDollar01);
        rbtDollar02 = ActivityCompat.requireViewById(this, R.id.rbtDollar02);
        rbtYen01 = ActivityCompat.requireViewById(this, R.id.rbtYen01);
        rbtYen02 = ActivityCompat.requireViewById(this, R.id.rbtYen02);
        imgFromCurrency = ActivityCompat.requireViewById(this, R.id.imgFromCurrency);
        imgToCurrency = ActivityCompat.requireViewById(this, R.id.imgToCurrency);

        rdgFromCurrency.setOnCheckedChangeListener((group, checkedId) -> fromListener());
        rdgToCurrency.setOnCheckedChangeListener((group, checkedId) -> toListener());

    }

    public void fromListener(){
        toRefresh();
        switch (rdgFromCurrency.getCheckedRadioButtonId()){
            case R.id.rbtEuro01:
                imgFromCurrency.setImageResource(R.drawable.ic_iconmonstr_currency_6);
                rbtEuro02.setEnabled(false);
                break;
            case R.id.rbtDollar01:
                imgFromCurrency.setImageResource(R.drawable.ic_iconmonstr_currency_1);
                rbtDollar02.setEnabled(false);
                break;
            case R.id.rbtYen01:
                imgFromCurrency.setImageResource(R.drawable.ic_iconmonstr_currency_12);
                rbtYen02.setEnabled(false);
                break;
        }
    }
    public void toListener(){
        fromRefresh();
        switch (rdgToCurrency.getCheckedRadioButtonId()){
            case R.id.rbtEuro02:
                imgToCurrency.setImageResource(R.drawable.ic_iconmonstr_currency_6);
                rbtEuro01.setEnabled(false);
                break;
            case R.id.rbtDollar02:
                imgToCurrency.setImageResource(R.drawable.ic_iconmonstr_currency_1);
                rbtDollar01.setEnabled(false);
                break;
            case R.id.rbtYen02:
                imgToCurrency.setImageResource(R.drawable.ic_iconmonstr_currency_12);
                rbtYen01.setEnabled(false);
                break;
        }
    }

    private void toRefresh() {
        rbtDollar02.setEnabled(true);
        rbtEuro02.setEnabled(true);
        rbtYen02.setEnabled(true);
    }

    private void fromRefresh(){
        rbtEuro01.setEnabled(true);
        rbtYen01.setEnabled(true);
        rbtDollar01.setEnabled(true);
    }
}

