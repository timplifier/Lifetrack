package com.example.lifetrack.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.lifetrack.R;
import com.example.lifetrack.adapters.notes.AdapterNotes;
import com.example.lifetrack.databinding.FragmentHomeBinding;
import com.example.lifetrack.models.NoteModel;
import com.example.lifetrack.utilities.app.App;
import com.example.lifetrack.utilities.interfaces.OnItemClickListener;

import java.util.List;


public class HomeFragment extends Fragment implements OnItemClickListener {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initListeners();


    }


    private void initAdapter() {
        App.getApp().getDb().noteDao().getAllNotes().observe(getViewLifecycleOwner(), taskList -> {
            AdapterNotes adapterNotes = new AdapterNotes((List<NoteModel>) taskList, this);
            binding.recyclerview.setAdapter(adapterNotes);
        });
    }


    private void initListeners() {
        binding.btnCreate.setOnClickListener(view -> {
            CreateNotesFragment createNotesFragment = new CreateNotesFragment();
            createNotesFragment.show(requireActivity().getSupportFragmentManager(), "");
        });
        binding.imToolbarProfileIcon.setOnClickListener(view ->
                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_profileFragment));


    }

    @Override
    public void onItemPress(NoteModel model) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        App.getApp().getDb().noteDao().delete(model);
                        Toast.makeText(getActivity(), "You have successfully deleted this task!", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setIcon(getResources().getDrawable(R.drawable.ic_delete_all_notes))
                .show();


    }

}