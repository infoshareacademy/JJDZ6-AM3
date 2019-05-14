<#include "common/base.ftl">
<#macro title>
   <h2><#if task??>Edit Task<#else>Create Task</#if></h2>
</#macro>
<#macro page_content>
        <div class="create-task">
            <form class="aui" method="POST">
                <div class="field-group">
                    <label for="title">Title<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="text" id="title" name="title" value="${task???then(task.title,"")}">
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
                        <label for="description">Give a description</label>
                        <textarea class="textarea" name="description" id="description"
                                  placeholder="Your description here..."
                        >${task???then(task.description,"")}</textarea>
                    </div>
                    <div class="field-group">
                        <label for="story-points">Story points</label>
                        <input class="text short-field" type="number" id="story-points"
                               name="storyPoints" value="${task???then(task.storyPoints,0)}">
                    </div>
                <div class="field-group">
                    <label for="name">Issue name</label>
                    <select class="select" id="type" name="type">
                        <#list types as type>
                            <option <#if task???then(task.type,"TASK") == type>selected</#if>
                                    value="${type}">${type.getName()}</option>
                        </#list>
                    </select>
                </div>
            <div class="field-group">
            <label for="name">Status</label>
            <select class="select" id="state" name="state">
            <#list states as state>
                <option <#if task???then(task.state,"TO_DO") == state>selected</#if>
                        value="${state}">${state.getName()}</option>
            </#list>
            </select>
            </div>
                <div class="buttons-container">
                    <div class="buttons">
                        <input class="aui-button aui-button-primary" type="button"
                               value="<#if task??>Update<#else>Create</#if>"
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
        const description = document.getElementById('description').value;
        const state = document.getElementById('state').value;
        const type = document.getElementById('type').value;
        const storyPoints = document.getElementById('story-points').value;
        // const projectId = document.getElementById('project').value;
        try {
            if (!id) {
                const { data } = await createTask({ title, description, state, type, storyPoints });
                const path = 'task?id=' + data.id;
                goTo(path)
            } else {
                const { data } = await updateTask(id, { title, description, state, type, storyPoints });
                const path = 'task?id=' + data.id;
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
