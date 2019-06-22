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
                <div class="field-group">
            <label for="assignee">Assignee:</label>
            <select class="select" id="assignee" name="assignee">
                <option value="">--Please choose an assignee--</option>
                <#list users as user>
                    <option
                        <#if task??>
                            <#if task.getAssignee()???then(task.getAssignee().getId(),"") == user.id>selected</#if>
                        </#if>
                            value="${user.id}">${user.fullName}</option>
                </#list>
            </select>
            </div>
                <div class="field-group">
                        <label for="description">Give a description</label>
                        <textarea class="textarea" name="description" id="description"
                                  placeholder="Your description here..."
                        >${task???then(task.description,"")}</textarea>
                    </div>
                    <div class="field-group">
                        <label for="story-points">Story points</label>
                        <input class="text short-field" type="number" id="story-points"
                               name="storyPoints"
                               value="<#if task??>${task.storyPoints???then(task.storyPoints, "")}</#if>">
                    </div>
                <div class="field-group">
                    <label for="type">Issue name</label>
                    <select class="select" id="type" name="type">
                        <#list types as type>
                            <option <#if task???then(task.type,"TASK") == type>selected</#if>
                                    value="${type}">${type.getName()}</option>
                        </#list>
                    </select>
                </div>
            <div class="field-group">
            <label for="status">Status</label>
            <select class="select" id="status" name="status">
            <#list statuses as status>
                <option <#if task???then(task.status,"TO_DO") == status>selected</#if>
                        value="${status}">${status.getName()}</option>
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
        const status = document.getElementById('status').value;
        const type = document.getElementById('type').value;
        const storyPoints = document.getElementById('story-points').value;
        const assigneeId = document.getElementById('assignee').value;
        try {
            if (!id) {
                const { data } = await createTask({
                    title,
                    description,
                    status,
                    type,
                    ...storyPoints ? { storyPoints } : { storyPoints: null },
                    assignee: assigneeId ? { id: assigneeId } : null
                });
                const path = 'task?id=' + data.id;
                goTo(path)
            } else {
                const { data } = await updateTask(id, {
                    title,
                    description,
                    status,
                    type,
                    ...storyPoints ? { storyPoints } : { storyPoints: null },
                    assignee: assigneeId ? { id: assigneeId } : null
                });
                const path = 'task?id=' + data.id;
                goTo(path)
            }
        } catch (e) {
            console.dir(e);
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
