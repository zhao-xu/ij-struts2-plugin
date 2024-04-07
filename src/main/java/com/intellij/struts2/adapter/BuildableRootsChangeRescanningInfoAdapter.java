package com.intellij.struts2.adapter;

import com.intellij.openapi.project.RootsChangeRescanningInfo;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.platform.workspace.jps.entities.LibraryId;
import com.intellij.platform.workspace.jps.entities.LibraryTableId;
import com.intellij.platform.workspace.jps.entities.ModuleId;
import com.intellij.platform.workspace.jps.serialization.impl.LibraryNameGenerator;
import com.intellij.util.SmartList;
import com.intellij.util.containers.SmartHashSet;
import com.intellij.workspaceModel.ide.impl.legacyBridge.library.LibraryBridge;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class BuildableRootsChangeRescanningInfoAdapter implements RootsChangeRescanningInfo {
    private final Set<ModuleId> modules = new SmartHashSet<>();
    private boolean hasInheritedSdk;
    private final List<Pair<String, String>> sdks = new SmartList<>();
    private final List<LibraryId> libraries = new SmartList<>();

    @NotNull
    public static BuildableRootsChangeRescanningInfoAdapter newInstance() {
        return new BuildableRootsChangeRescanningInfoAdapter();
    }

    @NotNull
    public BuildableRootsChangeRescanningInfoAdapter addModule(@NotNull com.intellij.openapi.module.Module module) {
        modules.add(new ModuleId(module.getName()));
        return this;
    }

    @NotNull
    public BuildableRootsChangeRescanningInfoAdapter addInheritedSdk() {
        hasInheritedSdk = true;
        return this;
    }

    @NotNull
    public BuildableRootsChangeRescanningInfoAdapter addSdk(@NotNull Sdk sdk) {
        sdks.add(new Pair<>(sdk.getName(), sdk.getSdkType().getName()));
        return this;
    }

    @NotNull
    public BuildableRootsChangeRescanningInfoAdapter addLibrary(@NotNull Library library) {
        LibraryId libraryId;
        if (library instanceof LibraryBridge) {
            libraryId = ((LibraryBridge)library).getLibraryId();
        }
        else {
            String level = library.getTable().getTableLevel();
            LibraryTableId libraryTableId = LibraryNameGenerator.INSTANCE.getLibraryTableId(level);
            String libraryName = library.getName();
            libraryId = new LibraryId(libraryName, libraryTableId);
        }
        libraries.add(libraryId);
        return this;
    }

    @NotNull
    Collection<ModuleId> getModules() {
        return modules;
    }

    public boolean hasInheritedSdk() {
        return hasInheritedSdk;
    }

    @NotNull
    Collection<Pair<String, String>> getSdks() {
        return sdks;
    }

    @NotNull
    Collection<LibraryId> getLibraries() {
        return libraries;
    }
}
