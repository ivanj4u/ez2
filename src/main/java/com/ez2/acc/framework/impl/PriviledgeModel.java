/*
 * This file is part of the EZ2 Accounting Project.
 * Author :
 * - Ivan Aribanilia
 * - Lukman Arizal
 * - Aristion Rizki
 * - Gabriel Sebastian
 *
 * Copyright (c) 2017. EZ2 Company.
 * Inc. All Rights Reserved. Terms of Use, Privacy and Trademark Guidelines
 */

package com.ez2.acc.framework.impl;

public interface PriviledgeModel {
    boolean setMode(int mode);

    boolean isAuthorizedToView();

    boolean isAuthorizedToUpdate();

    boolean isAuthorizedToDelete();

    boolean isAuthorizedToAdd();
}
