package com.example.ujjwal.broker.ContactUs.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ujjwal.broker.ContactUs.Model.Data.ContactUsData;
import com.example.ujjwal.broker.ContactUs.Model.RetrofitContactUsProvider;
import com.example.ujjwal.broker.ContactUs.Presenter.ContactUsPresenter;
import com.example.ujjwal.broker.ContactUs.Presenter.ContactUsPresenterImpl;
import com.example.ujjwal.broker.R;
import com.example.ujjwal.broker.helper.SharedPrefs;
import com.example.ujjwal.broker.helper.image_loader.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactUsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ContactUsFragment extends Fragment implements ContactUsView{


	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.progressBar)
	ProgressBar progressBar;
	@BindView(R.id.mobile1_name)
	TextView name1;
	@BindView(R.id.mobile2_name)
	TextView name2;
	@BindView(R.id.mobile3_name)
	TextView name3;
	@BindView(R.id.mobile1_number)
	TextView number1;
	@BindView(R.id.mobile2_number)
	TextView number2;
	@BindView(R.id.mobile3_number)
	TextView number3;
	@BindView(R.id.address)
	TextView address;
	@BindView(R.id.imageProgressBar)
	ProgressBar imageProgressBar;
	@BindView(R.id.imageView)
	ImageView imageView;
	@BindView(R.id.contact_us_layout)
	LinearLayout contactUsLayout;
	@BindView(R.id.phoneCard1)
	CardView phoneCard1;
	@BindView(R.id.phoneCard2)
	CardView phoneCard2;
	@BindView(R.id.phoneCard3)
	CardView phoneCard3;
	@BindView(R.id.locationCard)
	CardView locationCard;

	private ImageLoader imageLoader;
	private ContactUsPresenter contactUsPresenter;
	private SharedPrefs sharedPrefs;
	private OnFragmentInteractionListener mListener;

	public ContactUsFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_contact_us,container,false);

		ButterKnife.bind(this,view);

		toolbar.setNavigationIcon(R.drawable.ic_menu_camera);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().onBackPressed();
			}
		});

		sharedPrefs=new SharedPrefs(getContext());

		contactUsPresenter=new ContactUsPresenterImpl(this,new RetrofitContactUsProvider());
		contactUsPresenter.requestContactUs();
		// Inflate the layout for this fragment
		return view;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
											   + " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void showProgressBar(boolean show) {
		if (show){
			progressBar.setVisibility(View.VISIBLE);
			contactUsLayout.setVisibility(View.GONE);
		}else {
			progressBar.setVisibility(View.GONE);
			contactUsLayout.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void setContactUsData(ContactUsData contactUsData) {
		name1.setText(contactUsData.getName1());
		name2.setText(contactUsData.getName2());
		name3.setText(contactUsData.getName3());
		number1.setText(contactUsData.getNumber1());
		number2.setText(contactUsData.getNumber2());
		number3.setText(contactUsData.getNumber3());
		address.setText(contactUsData.getAddress());

		phoneCard1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent callIntent1=new Intent(Intent.ACTION_CALL);
				callIntent1.setData(Uri.parse("tel:"+sharedPrefs.getHelplineNumber1()));
				startActivity(callIntent1);
			}
		});

		phoneCard2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent callIntent1=new Intent(Intent.ACTION_CALL);
				callIntent1.setData(Uri.parse("tel:"+sharedPrefs.getHelplineNumber2()));
				startActivity(callIntent1);
			}
		});
		phoneCard3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent callIntent1=new Intent(Intent.ACTION_CALL);
				callIntent1.setData(Uri.parse("tel:"+sharedPrefs.getHelplineNumber3()));
				startActivity(callIntent1);
			}
		});

		imageLoader.LoadImage(contactUsData.getImage(), imageView, imageProgressBar);



	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
