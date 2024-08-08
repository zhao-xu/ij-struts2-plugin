package com.intellij.struts2.adapter;

import com.intellij.facet.FacetConfiguration;
import com.intellij.javaee.web.facet.WebFacetConfiguration;
import com.intellij.javaee.web.facet.WebRootData;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.containers.ContainerUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WebUtilImplAdapter {

    public static boolean isWebFacetConfigurationContainingFiles(FacetConfiguration configuration, Set<? extends VirtualFile> files) {
        if (configuration instanceof WebFacetConfiguration webConfiguration) {
            VirtualFile file = (VirtualFile) ContainerUtil.getFirstItem(files);
            if (file == null) {
                return false;
            } else {
                return isInsideWebRootsInternal(file, webConfiguration.getWebRoots()) || isUnderSourceRoots(file, webConfiguration.getSourceRoots());
            }
        } else {
            return false;
        }
    }

    private static boolean isInsideWebRootsInternal(VirtualFile source, List<? extends WebRootData> webRoots) {
        Iterator var2 = webRoots.iterator();

        VirtualFile root;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            WebRootData webRoot = (WebRootData)var2.next();
            root = webRoot.findFile();
        } while(root == null || !VfsUtilCore.isAncestor(root, source, false));

        return true;
    }

    private static boolean isUnderSourceRoots(VirtualFile file, List<String> urls) {
        Iterator var2 = urls.iterator();

        VirtualFile root;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            String url = (String)var2.next();
            root = VirtualFileManager.getInstance().findFileByUrl(url);
        } while(root == null || !VfsUtilCore.isAncestor(root, file, false));

        return true;
    }
}
