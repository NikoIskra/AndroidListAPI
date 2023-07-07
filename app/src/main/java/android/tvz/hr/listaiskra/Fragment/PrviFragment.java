package android.tvz.hr.listaiskra.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.tvz.hr.listaiskra.APII.Photos;
import android.tvz.hr.listaiskra.Prosli.ItemAdapter;
import android.tvz.hr.listaiskra.Database.ListItemDB;
import android.tvz.hr.listaiskra.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;




public class PrviFragment extends Fragment {


    private ArrayList<ListItemDB> mItemListDB=new ArrayList<>();

    public final ArrayList<Photos> mPhotosList =new ArrayList<>();

    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String twoPane="false";

    public ArrayList<Photos> getmPhotosList() {
        return mPhotosList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle=this.getArguments();
        if (bundle != null) {
            mItemListDB.add(bundle.getParcelable("listItem1"));
            mItemListDB.add(bundle.getParcelable("listItem2"));
            mItemListDB.add(bundle.getParcelable("listItem3"));
            mPhotosList.add(bundle.getParcelable("photo1"));
            mPhotosList.add(bundle.getParcelable("photo2"));
            mPhotosList.add(bundle.getParcelable("photo3"));


            twoPane=bundle.getString("twopane");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prvi, container, false);
        mRecyclerView=view.findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter=new ItemAdapter(mPhotosList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (twoPane=="true") {
                    Fragment drugiFragment= new DrugiFragment();
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("List item", mPhotosList.get(position));
                    drugiFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.frameLayout2, drugiFragment);
                    fragmentTransaction.commit();
                }
                else {
                    Fragment drugiFragment = new DrugiFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("List item", mPhotosList.get(position));
                    drugiFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.frameLayout, drugiFragment);
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }
}