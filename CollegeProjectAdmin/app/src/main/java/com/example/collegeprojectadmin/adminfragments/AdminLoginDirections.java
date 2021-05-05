package com.example.collegeprojectadmin.adminfragments;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.example.collegeprojectadmin.R;

public class AdminLoginDirections {
  private AdminLoginDirections() {
  }

  @NonNull
  public static NavDirections actionAdminLoginFragmentToAdminDetailsFragment() {
    return new ActionOnlyNavDirections(R.id.action_adminLoginFragment_to_adminDetailsFragment);
  }
}
