package com.example.collageproject.fragment.adminfragments;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.example.collageproject.R;

public class AdminDetailsDirections {
    private AdminDetailsDirections() {
    }

    @NonNull
    public static NavDirections actionAdminDetailsFragmentToAddGalleryImageFragment() {
        return new ActionOnlyNavDirections(R.id.action_adminDetailsFragment_to_addGalleryImageFragment);
    }

    @NonNull
    public static NavDirections actionAdminDetailsFragmentToAddRoutineFragment() {
        return new ActionOnlyNavDirections(R.id.action_adminDetailsFragment_to_addRoutineFragment);
    }

    @NonNull
    public static NavDirections actionAdminDetailsFragmentToAddNoticeFragment() {
        return new ActionOnlyNavDirections(R.id.action_adminDetailsFragment_to_addNoticeFragment);
    }

    @NonNull
    public static NavDirections actionAdminDetailsFragmentToAddClassesFragment() {
        return new ActionOnlyNavDirections(R.id.action_adminDetailsFragment_to_add_classesFragment);
    }
}
