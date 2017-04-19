package com.catalog.minut.minutecatalog;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catalog.minut.minutecatalog.entidades.Usuario;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FacebookLoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FacebookLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FacebookLoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CallbackManager callbackManager;
    private static OnFragmentInteractionListener mListener;
    private com.facebook.login.widget.LoginButton loginButton;

    public FacebookLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param listener Parameter 1.
     * @return A new instance of fragment FacebookLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FacebookLoginFragment newInstance(OnFragmentInteractionListener listener) {
        FacebookLoginFragment fragment = new FacebookLoginFragment();
        //Bundle args = new Bundle();
        //args.putSerializable(ARG_PARAM1,listener);
        //fragment.setArguments(args);
        mListener = listener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCallbackManager(CallbackManager.Factory.create());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebook_login, container, false);
        setLoginButton((LoginButton) view.findViewById(R.id.login_button));
        // Inflate the layout for this fragment
        getLoginButton().setReadPermissions("email");

        // If using in a fragment
        getLoginButton().setFragment(this);
        // Other app specific specialization

        // Callback registration
        getLoginButton().registerCallback(getCallbackManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                Profile profile = Profile.getCurrentProfile();

                try{
                    Usuario usr=new Usuario();

                    usr.setIdFacebook(profile.getId());
                    usr.setNomeUsuario(profile.getName());
                    getmListener().onFragmentInteraction(usr);

                }catch (Exception e){

                }finally {
                    LoginManager.getInstance().logOut();
                }




            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        return view;
    }







    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public void setCallbackManager(CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
    }

    public OnFragmentInteractionListener getmListener() {
        return mListener;
    }

    public void setmListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public LoginButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(LoginButton loginButton) {
        this.loginButton = loginButton;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener extends Serializable {
        // TODO: Update argument type and name
        void onFragmentInteraction(Usuario usr);
    }
}
