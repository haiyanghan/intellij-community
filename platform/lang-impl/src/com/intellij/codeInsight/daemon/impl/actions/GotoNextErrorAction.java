// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.codeInsight.daemon.impl.actions;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.actions.BaseCodeInsightAction;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.codeInsight.daemon.impl.GotoNextErrorHandler;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.impl.inspections.actions.TrafficLightGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class GotoNextErrorAction extends BaseCodeInsightAction implements DumbAware {

  private static final Logger LOGGER = Logger.getInstance(GotoNextErrorAction.class);

  public GotoNextErrorAction() {
    super(false);
  }

  @Override
  protected boolean isValidForLookup() {
    return true;
  }

  @Override
  protected @NotNull CodeInsightActionHandler getHandler() {
    return new GotoNextErrorHandler(true);
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    LOGGER.debug("GotoNextErrorAction: "+ e.getData(TrafficLightGroup.Companion.getINSPECTION_TYPED_ERROR()));
    super.actionPerformed(e);
  }

  @Override
  protected boolean isValidForFile(@NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
    return DaemonCodeAnalyzer.getInstance(project).isHighlightingAvailable(file);
  }
}