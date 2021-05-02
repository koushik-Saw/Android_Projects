package com.example.collageproject.fragment.adminfragments;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.example.collageproject.R;

public class AdminLoginDirections {
  private AdminLoginDirections() {
  }

  @NonNull
  public static NavDirections actionAdminLoginFragmentToAdminDetailsFragment() {
    return new ActionOnlyNavDirections(R.id.action_adminLoginFragment_to_adminDetailsFragment);
  }
}
