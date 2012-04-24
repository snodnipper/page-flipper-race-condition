package it.is.broken;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment1 extends Fragment {
	static final private int IMAGE = 1;
	private Handler mHandler;
	private int i;

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
    
	@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.first_menu, menu);
    }     
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment1, container, false);
        return root;
    }
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.first_menu_edit:
                Toast.makeText(this.getActivity(), "Cool bananas 1", Toast.LENGTH_SHORT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    } 	
    
    @Override
	public void onPause() {
		super.onPause();
		if (mHandler!=null) {
			mHandler.removeMessages(IMAGE);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		mHandler = new Handler() {
            public void handleMessage(Message msg) {
                // process incoming messages here
            	if (Fragment1.this == null) {
            		return;
            	}
            	ImageView iv = (ImageView)Fragment1.this.getView().findViewById(R.id.imageView1);
            	switch (i++) {
            		case 0:
            			iv.setImageResource(R.drawable.red);
            			break;
            		case 1:
            			iv.setImageResource(R.drawable.green);
            			break;
            		case 2:
            			iv.setImageResource(R.drawable.blue);
            			i=0;
            	
            	}
            	mHandler.sendEmptyMessageDelayed(IMAGE, 200);
            }			
		};
		mHandler.sendEmptyMessageDelayed(IMAGE, 200);		
	}   
	
}