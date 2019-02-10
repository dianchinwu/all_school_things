package tw.tcnr08.m0504;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M0504 extends ExpandableListActivity implements View.OnClickListener
{

    private static final String ITEM_NAME = "Item Name";
    private static final String ITEM_SUBNAME = "Item Subname";

    private TextView txtAns01;
    private String b_itemname;
    private String b_subitemname;
    private String b_txtdesc;
    private String b_txtsubdesc;
    private SimpleExpandableListAdapter mExpaListAdap;
    private final String TAG = "tcnr08=>";
    private ExpandableListView expandlist;
    private int id , subid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0504);
        setupViewComponent();
    }

    private void setupViewComponent()
    {
        txtAns01=(TextView)findViewById(R.id.m0504_t001);
        b_itemname=getString(R.string.m0504_titem);
        b_subitemname=getString(R.string.m0504_tsubitem);
        b_txtdesc=getString(R.string.m0504_tdesc);
        b_txtsubdesc=getString(R.string.m0504_tsubdesc);
        ExpandableListView expandlist = getExpandableListView();
        //expandlist.setOnGroupExpandListener(gel);
//        txtAns01.setText(getString(R.string.m0504_title));
        txtAns01.setOnClickListener(this);

        // 動態調整高度 抓取使用裝置尺寸
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // String px = displayMetrics.widthPixels + " x " +
        // displayMetrics.heightPixels;
        // String dp = displayMetrics.xdpi + " x " + displayMetrics.ydpi;
        // String density = "densityDpi = " + displayMetrics.densityDpi +
        // ", density=" + displayMetrics.density + ", scaledDensity = " +
        // displayMetrics.scaledDensity;
        // myname.setText(px + "\n" + dp + "\n" +density + "\n" +
        // newscrollheight);
        int newexpandlistheight = displayMetrics.heightPixels * 13 / 20; // 設定ScrollView使用尺寸的4/5
        int newexpandlistwidth = displayMetrics.widthPixels * 1; // 設定ScrollView使用尺寸的4/5
        expandlist.getLayoutParams().height = newexpandlistheight;  // 重定ScrollView大小
        expandlist.getLayoutParams().width = newexpandlistwidth;  // 重定ScrollView大小

        //宣告 list 內容 使用陣列 Map
        List<Map<String, String>> groupList = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childList2D = new ArrayList<List<Map<String, String>>>();
        //---第一層------------------
        for (int i = 0; i < 6; i++) {
            Map<String, String> group = new HashMap<String, String>();
            //String id1 = "m0504_s"+ String.format("%03d" , i );
            id = getResources().getIdentifier( "m0504_a"+ String.format("%02d" , i ) , "string" , getPackageName() );
            //Log.d(TAG , id1);
            //group.put(ITEM_NAME, b_itemname + i);
            group.put(ITEM_NAME,  getString(id) );
            //group.put(ITEM_SUBNAME, b_txtdesc + i);  //群組說明
            groupList.add(group);
            //---------------第二層-----------------------
            List<Map<String, String>> childList = new ArrayList<Map<String, String>>();
            try{
                for (int j = 0; j < 3; j++) {
                    Map<String, String> child = new HashMap<String, String>();
                    subid = getResources().getIdentifier( "m0504_a" + String.format("%02d" , i )+ String.format("%01d" , j ) , "string" , getPackageName() );
                    //child.put(ITEM_NAME, b_subitemname + i + j);
                    child.put(ITEM_NAME, getString(subid) );
                    //child.put(ITEM_SUBNAME, b_txtsubdesc + i + j);  //子選項說明
                    childList.add(child);
                }

            }catch (Exception e){
                return;
            }

            childList2D.add(childList);
            //---------第二層 end-----------
        }
        //--第一層 end----------------------

        // 設定 expandablelistview adapter
        mExpaListAdap = new SimpleExpandableListAdapter(this, groupList,
                R.layout.expandlist_group, //android.代表空的
//                android.R.layout.simple_expandable_list_item_2, //android.代表空的
                new String[]{ITEM_NAME, ITEM_SUBNAME},
                new int[]{R.id.m0504_t002, android.R.id.text2},
//                new String[]{ITEM_NAME, ITEM_SUBNAME},
//                new int[]{android.R.id.text1, android.R.id.text2},

//                childList2D, android.R.layout.simple_expandable_list_item_2,
                childList2D, R.layout.expandlist_item,  //此處設定子項目的layout
                new String[]{ITEM_NAME, ITEM_SUBNAME},
                new int[]{R.id.m0504_t003, android.R.id.text2});  //更改第一個子項目TextView顯示id
                //new String[]{ITEM_NAME, ITEM_SUBNAME},
                //new int[]{android.R.id.text1, android.R.id.text2});

        setListAdapter(mExpaListAdap);

    }

    @Override
    public void onClick(View v)
    {
        Uri uri = Uri.parse("tel:0800580995");  //台中市教育局
        Intent it = new Intent(Intent.ACTION_DIAL , uri);
        //Intent it = new Intent(Intent.ACTION_CALL, uri); 直接打電話出去
        startActivity(it);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
    {

//        String b_txtans = getString(R.string.m0504_title); // 聯絡方式
        int title_string =  getResources().getIdentifier( "m0504_a0"+ groupPosition + childPosition , "string" , getPackageName() ) ;
        String ans = "按此撥打  "+ getString(title_string) +"  分機: " + (groupPosition+1) + "0" + childPosition;

        txtAns01.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.ic_menu_call, 0, 0 , 0);  //左上右下
        txtAns01.setText(ans);
        txtAns01.setTextSize(18);
        txtAns01.setPadding(0,15,0,5);

        return super.onChildClick(parent, v, groupPosition, childPosition, id);//此行在最後，不要自己打，是override自動產生
    }


//    //控制只能打開一個组, 尚未完成
//    ExpandableListView.OnGroupExpandListener gel =new ExpandableListView.OnGroupExpandListener() {
//        @Override
//        public void onGroupExpand(int groupPosition) {
//            switch (groupPosition){
//                case 1:
//                    expandlist.expandGroup(1);
//                    break;
//                case 2:
//                    expandlist.expandGroup(2);
//                    break;
//                case 3:
//                    expandlist.expandGroup(3);
//                    break;
//                case 4:
//                    expandlist.expandGroup(4);
//                    break;
//                case 5:
//                    expandlist.expandGroup(5);
//                    break;
//            }
//
//        }
//    };

}

