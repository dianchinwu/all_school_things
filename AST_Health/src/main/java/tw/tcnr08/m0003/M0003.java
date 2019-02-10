package tw.tcnr08.m0003;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class M0003 extends ListActivity
{

    private String[] listFromResource , health_height ,health_weight;
    private ArrayList<Map<String, Object>> mList;
    private HashMap<String, Object> item;
    //    private String[] bmi_array;
    private double[] h;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0003);
        setupViewComponent();
    }

    private void setupViewComponent()
    {
        //學期
        listFromResource = getResources().getStringArray(R.array.m0003_semester);  //字串陣列
        //身高
        health_height = getResources().getStringArray(R.array.m0003_height);
        //體重
        health_weight = getResources().getStringArray(R.array.m0003_weight);
        //BMI值
        String[] bmi_array=new String[listFromResource.length];  //宣告陣列長度
        for(int i=0; i<listFromResource.length ; i++ ){
            double h = Double.parseDouble(health_height[i]);
            double bmi_height = h/100;
            double bmi_weight= Double.parseDouble(health_weight[i]);
            double bmi =bmi_weight/(bmi_height*bmi_height);
            bmi_array[i] =String.format("%.4f", bmi);
//            java保留小数--四舍五入--想保留几位就几位
//            String.format("%.nf",d);----表示保留N位！！！format("%.nf",double)
        }
        //BMI值建議
        //視力L/R

        mList = new ArrayList<Map<String, Object>>();
//        item = new HashMap<String, Object>();

        for (int i = 0; i < listFromResource.length; i++) {
            item = new HashMap<String, Object>();  //每一次new 新的HashMap來裝字串陣列
            item.put("semester", listFromResource[i]);
            item.put("height", health_height[i]);
            item.put("weight",health_weight[i]);
            item.put("bmi",bmi_array[i]);
            mList.add(item);
        }

//        for (int i = 0; i < health_height.length; i++) {
//
//            mList.add(item);
//        }

        SimpleAdapter adapter = new SimpleAdapter(this, mList,
                R.layout.health_list_item,
                new String[]{"semester","height","weight","bmi" },
                new int[]{R.id.m0003_tt00 ,R.id.m0003_tt01 ,R.id.m0003_tt02,R.id.m0003_tt03 }
        );

        // "height" ,"weight" , "bmi"  , "sug" ,"vision"
        // R.id.m0003_tt01 ,  R.id.m0003_tt02 ,  R.id.m0003_tt03 ,  R.id.m0003_tt04 ,  R.id.m0003_tt005

        setListAdapter(adapter);
        //----------------------------------------------------------------
        ListView listview = getListView();
        //自動調整螢幕高度
        listview.setTextFilterEnabled(true);
        listview.setOnItemClickListener(listviewOnItemClkLis);
    }

    AdapterView.OnItemClickListener listviewOnItemClkLis = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            //mTxtResult.setText(listFromResource[position]);
        }
    };

}
