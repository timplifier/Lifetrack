package com.example.lifetrack.ui.fragments.notes;

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
import com.example.lifetrack.utilities.interfaces.OnItemClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnItemClickListener {
    private FragmentHomeBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<NoteModel> list = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        initAdapter();


    }


    private void initAdapter() {
        db.collection("tasks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        NoteModel noteModel = document.toObject(NoteModel.class);
                        String taskName = noteModel.getTaskName();
                        String date = noteModel.getDate();
                        String frequency = noteModel.getFrequency();
                        list.add(noteModel);
                    }
                }
            }
        });
        AdapterNotes adapterNotes = new AdapterNotes(list, this);
        binding.recyclerview.setAdapter(adapterNotes);

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