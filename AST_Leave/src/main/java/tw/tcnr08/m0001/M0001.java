package tw.tcnr08.m0001;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

public class M0001 extends AppCompatActivity implements View.OnClickListener
{

    private Button startbtn , endbtn ,sendbtn;
    private Spinner timespn1 , timespn2, leavespn;
    private String time1,time2,leavechoose;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0001);
        setupViewComponent();
    }

    private void setupViewComponent()
    {
        startbtn =(Button)findViewById(R.id.m0001_bn01);
        endbtn =(Button)findViewById(R.id.m0001_bn02);
        sendbtn =(Button)findViewById(R.id.m0001_bn03);
        timespn1 =(Spinner)findViewById(R.id.m0001_spn01);
        timespn2 =(Spinner)findViewById(R.id.m0001_spn02);
        leavespn =(Spinner)findViewById(R.id.m0001_spn03);

                /*設定 spinner item 選項
        ArrayAdapter<CharSequence> adapSexList = ArrayAdapter.createFromResource(this,R.array.m0501_a001,android.R.layout.simple_spinner_item);
        adapSexList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s001.setAdapter(adapSexList);*/
        ArrayAdapter<CharSequence> adaptimeList1 = ArrayAdapter.createFromResource(this , R.array.time , android.R.layout.simple_spinner_item );
        adaptimeList1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adaptimeList2 = ArrayAdapter.createFromResource(this , R.array.time , android.R.layout.simple_spinner_item );
        adaptimeList2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapleaveList = ArrayAdapter.createFromResource(this , R.array.leave , android.R.layout.simple_spinner_item );
        adapleaveList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        timespn1.setAdapter(adaptimeList1);
        timespn2.setAdapter(adaptimeList2);
        leavespn.setAdapter(adapleaveList);
        timespn1.setOnItemSelectedListener(itemclick);  //注意是Spinner.OnItemSelectedListener的監聽事件, 不是onclicklistener , 會閃退
        timespn2.setOnItemSelectedListener(itemclick);
        leavespn.setOnItemSelectedListener(itemclick);

        startbtn.setOnClickListener(this);
        endbtn.setOnClickListener(this);
        sendbtn.setOnClickListener(this);
    }

    private Spinner.OnItemSelectedListener itemclick=new Spinner.OnItemSelectedListener(){  //注意是Spinner.OnItemSelectedListener的監聽事件, 不是onclicklistener , 會閃退
        // 正確生成的話會有兩個空的建構式, 1.onItemSelected(AdapterView<?> parent, View view, int position, long id)  2.onNothingSelected(AdapterView<?> parent)
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            time1 = parent.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
        }
    };

    @Override
    public void onClick(View v)
    {
        Calendar now = Calendar.getInstance();
        switch (v.getId()){
            case R.id.m0001_bn01:  //開始日期
                DatePickerDialog datePicDlg = new DatePickerDialog(M0001.this,
                        datePicDlgOnDateSelLis,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH));

//                datePicDlg.setTitle(getString(R.string.m0901_datetitle));
                datePicDlg.setMessage(getString(R.string.m0001_bn01));
                datePicDlg.setIcon(android.R.drawable.ic_dialog_info);
                datePicDlg.setCancelable(false);
                datePicDlg.show();
                break;

            case  R.id.m0001_bn02:  //結束日期
                DatePickerDialog datePicDlg2 = new DatePickerDialog(M0001.this,
                        datePicDlgOnDateSelLis2,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH));

//                datePicDlg.setTitle(getString(R.string.m0901_datetitle));
                datePicDlg2.setMessage(getString(R.string.m0001_bn02));
                datePicDlg2.setIcon(android.R.drawable.ic_dialog_info);
                datePicDlg2.setCancelable(false);
                datePicDlg2.show();
                break;

            case R.id.m0001_bn03:  //送出
                break;
        }
    }

    private DatePickerDialog.OnDateSetListener datePicDlgOnDateSelLis = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            startbtn.setText( Integer.toString(year) + "/"+
                    Integer.toString(monthOfYear + 1) +  "/"+
                    Integer.toString(dayOfMonth)  );
            //getString(R.string.n_yy)+getString(R.string.n_mm) +getString(R.string.n_dd)
        }
    };

    private DatePickerDialog.OnDateSetListener datePicDlgOnDateSelLis2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            endbtn.setText( Integer.toString(year) + "/"+
                    Integer.toString(monthOfYear + 1) +  "/"+
                    Integer.toString(dayOfMonth)  );
            //getString(R.string.n_yy)+getString(R.string.n_mm) +getString(R.string.n_dd)
        }
    };

}
