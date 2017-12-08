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

package com.ez2.acc.framework.listener;

public interface DetailCallbackListener {

    void onAfterAdded(Object pojo);

    void onAfterUpdated(Object pojo);

    void onAfterViewed();

    void onCancel();
}
