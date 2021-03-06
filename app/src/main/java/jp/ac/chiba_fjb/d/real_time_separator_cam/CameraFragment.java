package jp.ac.chiba_fjb.d.real_time_separator_cam;


import android.app.Activity;
import android.graphics.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment {

	static int i = 0;
	private static String foldername = "デフォルト";
	private static CameraPreview mCamera;
	Permission mPermission;


	public CameraFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_camera, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		//インスタンスの取得
		TextureView textureView = (TextureView) getView().findViewById(R.id.textureView);
		//カメラプレビュー用クラスの作成
		mCamera = new CameraPreview();
		mCamera.setTextureView(textureView);
		mCamera.open(0);

		//撮影ボタン
		ImageButton photog = (ImageButton)getView().findViewById(R.id.Photo);
        photog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ms = (TextView) getView().findViewById(R.id.ms);
                ms.setText("撮影しました");
                mCamera.takePicture();


            }
        });

        Button menu = (Button)getView().findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ダイアログを表示する
                DialogFragment newFragment = new MenuFragment();
                newFragment.show(getActivity().getSupportFragmentManager(),null);


            }
        });



	}

	static String HddSave(){
		i++;
		String savept =  Environment.getExternalStorageDirectory() + "/" +foldername+"/"+foldername+String.valueOf(i)+".jpg";
		return savept;
	}


	void setFolderName(String name){
		i = 0;
		foldername = name;
	}

}
