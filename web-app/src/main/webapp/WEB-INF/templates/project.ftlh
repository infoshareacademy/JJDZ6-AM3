<#include "common/base.ftl">
<#macro title>
       <div class="project-header-main">
        <div class="project-header-image">
            <span class="tag ${project.type?lower_case}">${project.type.getName()}</span>
        </div>
        <div class="project-header-content">
            <h1 class="project-title">
                ${project.title}
            </h1>
            <small class="project-id">Id: ${project.id}</small>
        </div>
    </div>
</#macro>
<#macro page_content>
<div class="project-view">
    <div class="project-view-content">
        <div class="project-view-main-column">
            <h4 class="description">Description</h4>
        </div>
        <div class="project-view-side-column">
            <h4 class="title">Acceptance Criteria</h4>
        </div>
    </div>
</div>
</#macro>
<@display_page/>
