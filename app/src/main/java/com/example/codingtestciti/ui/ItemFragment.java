package com.example.codingtestciti.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.codingtestciti.R;
import com.example.codingtestciti.adapter.MyItemRecyclerViewAdapter;
import com.example.codingtestciti.model.AccessInfo;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    static final String TAG = ItemFragment.class.getSimpleName();
    private ProgressBar progressBar;
    private Button refreshButton;
    private MyItemRecyclerViewAdapter adapter;

    private List<AccessInfo.Item> list = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        refreshButton = view.findViewById(R.id.btn_refresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyItemRecyclerViewAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        setRefreshButton();

        makeNetWorkCall();

        return view;
    }

    /**
     * Set refresh button listener in order to make a call again.
     */
    private void setRefreshButton() {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNetWorkCall();
            }
        });
    }

    /**
     * Used Volley to make network call on get method
     * Map response in an object and notify the adapter
     * If Error occur snack bar will populate
     */
    private void makeNetWorkCall() {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        String URL = "https://www.googleapis.com/books/v1/volumes?q=2020";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                AccessInfo accessInfo = gson.fromJson(response, AccessInfo.class);
                list.addAll(accessInfo.getItems());
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Snackbar snackBar = Snackbar.make(Objects.requireNonNull(getActivity()).findViewById(android.R.id.content),
                        "Unable to fetch result", Snackbar.LENGTH_LONG);
                snackBar.show();
            }
        });

        queue.add(stringRequest);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
