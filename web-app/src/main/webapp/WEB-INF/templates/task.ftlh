<#include "common/base.ftl">
<#macro title>
       <div class="task-header-main">
        <div class="task-header-image">
            <span class="tag ${task.type?lower_case}">${task.type.getName()}</span>
        </div>
        <div class="task-header-content">
            <h1 class="task-title">
                ${task.title}
            </h1>
            <small class="task-id">Id: ${task.id}</small>
        </div>
    </div>
</#macro>
<#macro page_content>
<div class="task-view">
    <div class="task-view-content">
        <div class="task-view-main-column">
            <h4 class="description">Description</h4>
            <p>${task.description}</p>
        </div>
        <div class="task-view-side-column">
            <h4 class="title">Acceptance Criteria</h4>
        </div>
    </div>
</div>
</#macro>
<@display_page/>
