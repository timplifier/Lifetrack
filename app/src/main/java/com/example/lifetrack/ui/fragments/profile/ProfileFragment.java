package com.example.lifetrack.ui.fragments.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.lifetrack.R;
import com.example.lifetrack.databinding.FragmentProfileBinding;
import com.example.lifetrack.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfileFragment extends Fragment {
    private static final String TAG = "Profile Fragment";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FragmentProfileBinding binding;
    private DocumentReference profileReference = db.collection("users").document("User profile");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        retrieveData();

    }

    private void retrieveData() {
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                db.collection("users")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        UserModel userModel = document.toObject(UserModel.class);
                                        String name = userModel.getName();
                                        String surname = userModel.getSurname();
                                        binding.etName.setText(name);
                                        binding.etSurname.setText(surname);
                                        Log.e("hm,seems guchi", "onComplete: " + "/n" + userModel.getName() + "/n" + userModel.getSurname());
                                    }
                                } else {
                                    Log.e("no data to be read", "onComplete: " + task.getException());

                                }


                            }
                        });

            }
        });
    }


    private void initListeners() {
        binding.btnChangesSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.etName.getText().toString();
                String surname = binding.etSurname.getText().toString();
                UserModel user = new UserModel(name, surname);
                db.collection("users")
                        .document("User profile")
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(requireContext(), "You have successfully applied changes to your profile", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
        binding.imToolbarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment_to_homeFragment);
            }
        });

    }


}
