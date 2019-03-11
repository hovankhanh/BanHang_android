package com.example.khanhho.drawer.Activirty;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.khanhho.drawer.Adapter.LoaispAdapter;
import com.example.khanhho.drawer.Adapter.SanphamAdapter;
import com.example.khanhho.drawer.Model.Loaisp;
import com.example.khanhho.drawer.Model.Sanpham;
import com.example.khanhho.drawer.R;
import com.example.khanhho.drawer.ultil.Checkconnection;
import com.example.khanhho.drawer.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ViewFlipper viewFlipper;
    Toolbar toolbar;
    NavigationView navigationView;
    //listview menu bar
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    ListView listView;
    int idloaisp = 0;
    String tenloaisp = "";
    String hinhanhloaisp = "";
    TextView test;
    String khanh = "";

// recycleview
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    RecyclerView recycleViewManHinhChinh;
    int idsanpham = 0;
    String tensanpham = "";
    String hinmhanhsanpham = "";
    int giasanpham = 0;
    String motasanpham = "";
    int idlsanpham = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (Checkconnection.haveNetworkConnection(getApplicationContext())){
            ActionViewFlipper();
            setSupportActionBar(toolbar);
            Getdulieuloaisp();
            Getdulieusanphammoinhat();
        }else {
            Checkconnection.ShowToast_Short(getApplicationContext(),"ban hay tra lai ket noi");
            finish();
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void Getdulieusanphammoinhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    for (int i = 0; i <response.length(); i++){
                        try{
                            JSONObject jsonObject =response.getJSONObject(i);
                            idsanpham = jsonObject.getInt("id");
                            tensanpham = jsonObject.getString("tensp");
                            hinmhanhsanpham = jsonObject.getString("hinhanhsp");
                            motasanpham = jsonObject.getString("motasp");
                            giasanpham= jsonObject.getInt("giasp");
                            idlsanpham = jsonObject.getInt("idloaisp");
                            mangsanpham.add(new Sanpham(idsanpham,tensanpham,giasanpham, hinmhanhsanpham,motasanpham,idlsanpham));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Checkconnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });

        requestQueue.add(jsonArrayRequest);
    }


    private void Getdulieuloaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    for (int i = 0; i <response.length(); i++){
                        try{
                            JSONObject jsonObject =response.getJSONObject(i);
                            idloaisp = jsonObject.getInt("id");
                            tenloaisp = jsonObject.getString("tenloaisp");
                            hinhanhloaisp = jsonObject.getString("hinhanhloaisp");
                            mangloaisp.add(new Loaisp(idloaisp,tenloaisp,hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Checkconnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangQuangcao = new ArrayList<>();
        mangQuangcao.add("https://tinhte.cdnforo.com/store/2014/08/2572609_Hinh_2.jpg");
        mangQuangcao.add("http://i.imgur.com/DvpvklR.png");
        mangQuangcao.add("https://tinhte.cdnforo.com/store/2014/08/2572609_Hinh_2.jpg");
        mangQuangcao.add("http://i.imgur.com/DvpvklR.png");
        for (int i = 0; i < mangQuangcao.size(); i++) {
            ImageView imageView = new ImageView(this);
            Picasso.with(getApplicationContext()).load(mangQuangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
            viewFlipper.setFlipInterval(5000);
            viewFlipper.setAutoStart(true);
            Animation  animation_slide_in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
            Animation  animation_slide_out = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
            viewFlipper.setAnimation(animation_slide_in);
            viewFlipper.setAnimation(animation_slide_out);


    }

    private void Anhxa(){
        test = (TextView)findViewById(R.id.test);
        listView  = (ListView) findViewById(R.id.liviewtest);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewlipper);

        mangloaisp  = new ArrayList<>();
        mangloaisp.add(0,   new Loaisp(0,"Trang chinh", "http://i.imgur.com/DvpvklR.png"));
        loaispAdapter = new LoaispAdapter(mangloaisp,this);
        listView.setAdapter(loaispAdapter);

        recycleViewManHinhChinh = (RecyclerView)findViewById(R.id.recycleview);
        mangsanpham  = new ArrayList<>();
//        test.setText(tensanpham);
        sanphamAdapter = new SanphamAdapter(this,mangsanpham);
        recycleViewManHinhChinh.setAdapter(sanphamAdapter);
        recycleViewManHinhChinh.setLayoutManager(new LinearLayoutManager(this));
        recycleViewManHinhChinh.setLayoutManager(new GridLayoutManager(this,2));
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            Toast.makeText(this, "This is a message", Toast.LENGTH_LONG).show();
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
