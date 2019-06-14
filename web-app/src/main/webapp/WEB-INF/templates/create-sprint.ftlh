<#include "common/base.ftl">
<#macro title>
   <h2><#if sprint??>Edit Sprint<#else>Create Sprint</#if></h2>
</#macro>
<#macro page_content>
        <div class="create-sprint">
            <form class="aui" method="POST">
                <div class="field-group">
                    <label for="title">Title<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="text" id="title" name="title" value="${sprint???then(sprint.title,"")}">
                    <div class="error"></div>
                </div>
            <#--<div class="field-group">-->
            <#--<label for="name">Project name</label>-->
            <#--<select class="select" id="project" name="projectId">-->
            <#--<#list projects as project>-->
            <#--<option value="${project.id}">${project.name}</option>-->
            <#--</#list>-->
            <#--</select>-->
            <#--</div>-->
                <div class="field-group">
                    <label for="title">Start Date<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="text" id="start-date" name="start-date" value="${sprint???then(sprint.start,"")}">
                    <div class="error"></div>
                </div>
                <div class="field-group">
                    <label for="title">End Date<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="text" id="end-date" name="end-date" value="${sprint???then(sprint.end,"")}">
                    <div class="error"></div>
                    <div class="field-group">
                        <label for="name">Task</label>
                        <select class="select" id="type" name="type">
                            <#list types as type>
                                <option <#if task???then(task.type,"TASK") == type>selected</#if>
                                        value="${type}">${type.getName()}</option>
                            </#list>
                        </select>
                    </div>
                <div class="buttons-container">
                    <div class="buttons">
                        <input class="aui-button aui-button-primary" type="button"
                               value="<#if sprint??>Update<#else>Create</#if>"
                               id=submit>
                        <a class="cancel" href="/">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
</#macro>

<@display_page/>

<script type="text/javascript">
    const submit = document.getElementById('submit');
    const clearErrors = () => document.querySelectorAll('.error').forEach(e => e.innerHTML = "");
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    const onSubmit = async (e) => {
        clearErrors();
        e.preventDefault();
        const title = document.getElementById('title').value;
        const startDate = document.getElementById('start-date').value;
        const endDate = document.getElementById('end-date').value;
        const sprintId = document.getElementById('sprint').value;
        try {
            if (!id) {
                const { data } = await createSprint({ title, startDate, endDate, sprintId });
                const path = 'sprint?id=' + data.id;
                goTo(path)
            } else {
                const { data } = await updateSprint(id, { title, startDate, endDate, sprintId });
                const path = 'sprint?id=' + data.id;
                goTo(path)
            }
        } catch (e) {
            if (e.status = Status.VALIDATION_ERROR) {
                e.fieldErrors.forEach(error => document
                        .querySelector('[name=' + error.field + ']')
                        .nextElementSibling
                        .innerHTML = error.message);
            }
            console.error(e.message)
        }
    };
    submit.addEventListener('click', onSubmit);
</script>
</html>
