package es.iessaladillo.maria.exchange;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText lblAmount;
    private RadioGroup rdgFromCurrency;
    private RadioGroup rdgToCurrency;
    private RadioButton rbtEuro01;
    private RadioButton rbtEuro02;
    private RadioButton rbtDollar01;
    private RadioButton rbtDollar02;
    private RadioButton rbtPound01;
    private RadioButton rbtPound02;
    private ImageView imgFromCurrency;
    private ImageView imgToCurrency;
    private Button btnExchange;

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
        rbtEuro01 = ActivityCompat.requireViewById(this, R.id.rbFromEuro);
        rbtEuro02 = ActivityCompat.requireViewById(this, R.id.rbToEuro);
        rbtDollar01 = ActivityCompat.requireViewById(this, R.id.rbFromDollar);
        rbtDollar02 = ActivityCompat.requireViewById(this, R.id.rbToDollar);
        rbtPound01 = ActivityCompat.requireViewById(this, R.id.rbFromPound);
        rbtPound02 = ActivityCompat.requireViewById(this, R.id.rbToPound);
        imgFromCurrency = ActivityCompat.requireViewById(this, R.id.imgFrom);
        imgToCurrency = ActivityCompat.requireViewById(this, R.id.imgTo);
        btnExchange = ActivityCompat.requireViewById(this, R.id.btnExchange);

        rdgFromCurrency.setOnCheckedChangeListener((group, checkedId) -> fromListener());
        rdgToCurrency.setOnCheckedChangeListener((group, checkedId) -> toListener());
        btnExchange.setOnClickListener(l -> toExchange());
        lblAmount.setOnClickListener(this);


    }

    private void toExchange() {
        final double EUR_USD = 1.15990;
        final double EUR_GBP = 0.887689;
        final double USD_GBP = 0.767312;
        final char symbol_euro = '€', symbol_dollar = '$', symbol_pound = '£';

        double res=0, op1=0;
        String result = "";

        try{
            //We transform the text that is introduced to Double
            op1 = Double.parseDouble(lblAmount.getText().toString());
        }catch(NumberFormatException e){
           lblAmount.setText(R.string.amountDefault);
        }
        //The result is calculated and the result with its corresponding symbols is introduced in the chain
        switch(rdgFromCurrency.getCheckedRadioButtonId()){
            case R.id.rbFromEuro:
                switch (rdgToCurrency.getCheckedRadioButtonId()) {
                    case R.id.rbToDollar:
                        res = op1 * EUR_USD;
                        result = result.concat(String.format("%.2f%c -> %c%.2f", op1, symbol_euro, symbol_dollar, res));
                        break;
                    case R.id.rbToPound:
                        res = op1 * EUR_GBP;
                        result = result.concat(String.format("%.2f%c -> %c%.2f", op1, symbol_euro, symbol_pound, res));
                        break;
                }
                break;
            case R.id.rbFromDollar:
                switch(rdgToCurrency.getCheckedRadioButtonId()) {
                    case R.id.rbToEuro:
                        res = op1 / EUR_USD;
                        result = result.concat(String.format("%c%.2f -> %.2f%c", symbol_dollar, op1, res, symbol_euro));
                        break;
                    case R.id.rbToPound:
                        res = op1 * USD_GBP;
                        result = result.concat(String.format("%c%.2f -> %c%.2f", symbol_dollar, op1, symbol_pound, res));
                        break;
                }
                break;
            case R.id.rbFromPound:
                switch(rdgToCurrency.getCheckedRadioButtonId()) {
                    case R.id.rbToDollar:
                        res = op1 / USD_GBP;
                        result = result.concat(String.format("%c%.2f -> %c%.2f", symbol_pound, op1, symbol_dollar, res));
                        break;
                    case R.id.rbToEuro:
                        res = op1 / EUR_GBP;
                        result= result.concat(String.format("%c%.2f -> %.2f%c", symbol_pound, op1, res, symbol_euro));
                        break;
                }
            break;
        }

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    private void fromListener(){
        toRefresh(); //It cleans the disabled buttons that have been previously
        //The currency symbol is changed and the corresponding buttons are disabled
        switch (rdgFromCurrency.getCheckedRadioButtonId()){
            case R.id.rbFromEuro:
                imgFromCurrency.setImageResource(R.drawable.ic_euro);
                rbtEuro02.setEnabled(false);
                break;
            case R.id.rbFromDollar:
                imgFromCurrency.setImageResource(R.drawable.ic_dollar);
                rbtDollar02.setEnabled(false);
                break;
            case R.id.rbFromPound:
                imgFromCurrency.setImageResource(R.drawable.ic_pound);
                rbtPound02.setEnabled(false);
                break;
        }
    }
    private void toListener(){
        fromRefresh();
        switch (rdgToCurrency.getCheckedRadioButtonId()){
            case R.id.rbToEuro:
                imgToCurrency.setImageResource(R.drawable.ic_euro);
                rbtEuro01.setEnabled(false);
                break;
            case R.id.rbToDollar:
                imgToCurrency.setImageResource(R.drawable.ic_dollar);
                rbtDollar01.setEnabled(false);
                break;
            case R.id.rbToPound:
                imgToCurrency.setImageResource(R.drawable.ic_pound);
                rbtPound01.setEnabled(false);
                break;
        }
    }

    private void toRefresh() {
        rbtDollar02.setEnabled(true);
        rbtEuro02.setEnabled(true);
        rbtPound02.setEnabled(true);
    }

    private void fromRefresh(){
        rbtEuro01.setEnabled(true);
        rbtPound01.setEnabled(true);
        rbtDollar01.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        lblAmount.selectAll();
    }
}

