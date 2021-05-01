package com.example.schoolproject.fragment.Admin;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.schoolproject.R;

public class AddGalleryImageDirections {
  private AddGalleryImageDirections() {
  }

  @NonNull
  public static NavDirections actionAddGalleryImageFragmentToGalleryFragment() {
    return new ActionOnlyNavDirections(R.id.action_addGalleryImageFragment_to_galleryFragment);
  }
}
