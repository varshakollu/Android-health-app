package com.example.varsha.navtry;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Varsha on 5/6/2016.
 */
public class BarcodeUPC extends AppCompatActivity {

    private TextView jsondata;
    private EditText get_upc_code;
    private ListView lvproducts;
    String parsed_json_string;
    String received_barcode_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcodeupc);

        received_barcode_val = getIntent().getExtras().getString("barcode_value");
        System.out.println("received barcode value" + received_barcode_val);
        lvproducts = (ListView)findViewById(R.id.lvproducts);
        new RestOperation().execute("http://www.searchupc.com/handlers/upcsearch.ashx?request_type=3&access_token=5A033DF4-E5E2-4C42-BDF1-0255FC646F3B&upc="+received_barcode_val);
    }

    public class RestOperation extends AsyncTask<String, String, List<ProductModel>> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected List<ProductModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String rawJson = buffer.toString();

                JSONObject myjsonobject = new JSONObject(rawJson);

                System.out.println("*****JSONOBJECT LENGTH*****" + myjsonobject.length());

                List<ProductModel> productModelList = new ArrayList<>();


                for(int i=0; i<myjsonobject.length(); i++){

                    JSONObject jsonobj = myjsonobject.getJSONObject(String.valueOf(i));
                    ProductModel productModel = new ProductModel();
                    productModel.setProductname(jsonobj.getString("productname"));
                    productModel.setImageurl(jsonobj.getString("imageurl"));
                    productModel.setPrice(jsonobj.getString("price"));
                    productModel.setCurrency(jsonobj.getString("currency"));
                    productModel.setStorename(jsonobj.getString("storename"));

                    Log.i("log_tag", "Product Name" + jsonobj.getString("productname") +
                                    ", Image URL" + jsonobj.getString("imageurl") +
                                    ", Price" + jsonobj.getString("price") +
                                    "Currency" + jsonobj.getString("currency") +
                                    "Store Name" + jsonobj.getString("storename")
                    );
                   productModelList.add(productModel);
                }

                return productModelList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ProductModel> result) {
            super.onPostExecute(result);
            ProductAdapter adapter = new ProductAdapter(getApplicationContext(), R.layout.row_layout, result);
            lvproducts.setAdapter(adapter);
        }
    }

    public class ProductAdapter extends ArrayAdapter {

        public List<ProductModel> productModelList;
        private int resource;
        private LayoutInflater inflater;

        public ProductAdapter(Context context, int resource, List<ProductModel> objects) {
            super(context, resource, objects);
            productModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = inflater.inflate(resource, null);
            }

            ImageView imageButton;
            TextView productname;
            TextView imageurl;
            TextView price;
            TextView currency;
            TextView storename;

            imageButton = (ImageView)convertView.findViewById(R.id.imageButton);
            productname = (TextView)convertView.findViewById(R.id.productname);
            imageurl =(TextView) convertView.findViewById(R.id.imageurl);
            price =(TextView) convertView.findViewById(R.id.price);
            currency =(TextView) convertView.findViewById(R.id.currency);
            storename =(TextView) convertView.findViewById(R.id.storename);

            productname.setText(productModelList.get(position).getProductname());
            imageurl.setText("Image URL: "+productModelList.get(position).getImageurl());
            price.setText("Price: "+productModelList.get(position).getPrice());
            currency.setText("Currency: "+productModelList.get(position).getCurrency());
            storename.setText("Store Name: "+productModelList.get(position).getStorename());

            return convertView;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if( id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
