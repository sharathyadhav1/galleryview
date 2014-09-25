package com.sharath.views;



import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GalleryView extends Activity {
    Integer[] pics = {
    		R.drawable.antartica1,
    		R.drawable.antartica2,
    		R.drawable.antartica3,
    		R.drawable.antartica4,
    		R.drawable.antartica5,
    		R.drawable.antartica6,
    		R.drawable.antartica7,
    		R.drawable.antartica8,
    		R.drawable.antartica9,
    		R.drawable.antartica10
    };
    ImageView imageView;
    ArrayList<imageitem> ij = new ArrayList<imageitem>();
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Gallery ga = (Gallery)findViewById(R.id.Gallery01);
        ga.setAdapter(new ImageAdapter(this));
        
        imageView = (ImageView)findViewById(R.id.ImageView01);
        for(int i=0;i<pics.length;i++){
        	imageitem iv = new imageitem();
        	iv.setImageurl((pics[i]));
        	ij.add(iv);
        }
       
        
        
        ga.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getBaseContext(), 
						"You have selected picture " + (arg2+1) + " of Antartica", 
						Toast.LENGTH_SHORT).show();
				
				 Log.i("ijsize", ""+ij.size());
			
			}
        	
        });
       // ga.
    }
    
    
    public class ImageAdapter extends BaseAdapter {

    	private Context ctx;
    	int imageBackground;
    	LayoutInflater layoutInflat;
    	Holder holder = new Holder();
    	
    	public ImageAdapter(Context c) {
			ctx = c;
			layoutInflat = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}

		@Override
    	public int getCount() {
    		
    		return pics.length;
    	}

    	@Override
    	public Object getItem(int arg0) {
    		
    		return arg0;
    	}

    	@Override
    	public long getItemId(int arg0) {
    		
    		return arg0;
    	}

    	@Override
    	public View getView(final int arg0, View convertView, ViewGroup arg2) {
    		View view = convertView;
    		if (view == null) {
    			view = layoutInflat.inflate(R.layout.galleryrow, null);
    			holder.iv = (ImageView)view.findViewById(R.id.imageView1);
    			holder.ck = (CheckBox)view.findViewById(R.id.checkBox1);
    		}else{
    			holder = (Holder )view.getTag();
    		}
    		
    		holder.iv.setImageResource(pics[arg0]);
    		holder.iv.setBackgroundResource(imageBackground);
    		
    		holder.ck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(!isChecked){
						ij.remove(arg0);
					}
					else{
						imageitem ivitem = new imageitem();
						ivitem.setImageurl(pics[arg0]);
						ij.add(ivitem);
					}
				}
			});
    		

    		
    		return view;
    	}
    	public class Holder {
    		ImageView iv;
    		CheckBox ck;
    	}

    }
}