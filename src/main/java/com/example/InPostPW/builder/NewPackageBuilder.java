package com.example.InPostPW.builder;

import com.example.InPostPW.dto.NewPackageFormDto;
import com.example.InPostPW.model.Package;
import org.json.JSONException;

public interface NewPackageBuilder {

    Package createNewPackage(NewPackageFormDto packageFormDto) throws JSONException;
}
