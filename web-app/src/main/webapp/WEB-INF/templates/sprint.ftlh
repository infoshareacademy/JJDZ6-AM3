<#include "common/base.ftl">
<#macro title>
       <div class="sprint-header-main">
        <div class="sprint-header-image">
            <span class="tag ${sprint.type?lower_case}">${sprint.type.getName()}</span>
        </div>
        <div class="sprint-header-content">
            <h1 class="sprint-title">
                ${sprint.title}
            </h1>
            <small class="sprint-id">Id: ${sprint.id}</small>
        </div>
    </div>
</#macro>
<#macro page_content>
<div class="sprint-view">
    <div class="sprint-view-content">
        <div class="sprint-view-main-column">
            <h4 class="description">Description</h4>
        </div>
        <div class="sprint-view-side-column">
            <h4 class="title">Acceptance Criteria</h4>
        </div>
    </div>
</div>
</#macro>
<@display_page/>
