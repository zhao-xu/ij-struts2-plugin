package com.intellij.struts2.adapter;

import com.intellij.codeInsight.navigation.DomGotoRelatedItem;
import com.intellij.navigation.GotoRelatedItem;
import com.intellij.psi.PsiElement;
import com.intellij.util.NotNullFunction;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.DomElement;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NavigationGutterIconBuilderAdapter {
    public static final NotNullFunction<DomElement, Collection<? extends PsiElement>> DEFAULT_DOM_CONVERTOR =
            o -> ContainerUtil.createMaybeSingletonList(o.getXmlElement());

    public static final NotNullFunction<DomElement, Collection<? extends GotoRelatedItem>> DOM_GOTO_RELATED_ITEM_PROVIDER = dom -> {
        if (dom.getXmlElement() != null) {
            return List.of(new DomGotoRelatedItem(dom));
        }
        return Collections.emptyList();
    };
}
