<#include "common/base.ftl">
<#macro title>
   <h2><#if task??>Edit Sprint<#else>Create Sprint</#if></h2>
</#macro>
<#macro page_content>
        <div class="create-task">
            <form class="aui" method="POST">
                <div class="field-group">
                    <label for="name">Name<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="text" id="name" name="name" value="${task???then(task.name,"")}">
                    <div class="error"></div>
                </div>
                <div class="field-group">
            </div>
            <input class="aui-date-picker" id="demo-range-2" type="date" max="2029-01-25" min="2011-12-25" />
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
    AJS.$(document).ready(function() {
        AJS.$('#demo-range-2').datePicker({'overrideBrowserDefault': true});
    });

    const submit = document.getElementById('submit');
    const clearErrors = () => document.querySelectorAll('.error').forEach(e => e.innerHTML = "");
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    const onSubmit = async (e) => {
        clearErrors();
        e.preventDefault();
        const name = document.getElementById('name').value;
        const description = document.getElementById('description').value;
        const status = document.getElementById('status').value;
        const type = document.getElementById('type').value;
        const storyPoints = document.getElementById('story-points').value;
        const assigneeId = document.getElementById('assignee').value;
        try {
            if (!id) {
                const { data } = await createTask({
                    name,
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
                    name,
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
