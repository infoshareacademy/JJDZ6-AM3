<#include "common/base.ftl">
<#macro title>
   <h2>Backlog</h2>
</#macro>
<#macro page_content>
<a href="/create-sprint">+ Create New Sprint</a>
<table class="aui">
    <thead>
        <tr>
            <th id="id">id</th>
            <th id="name">Type</th>
            <th id="title">Title</th>
            <th id="description">Description</th>
            <th id="name">State</th>
            <th id="story-points">Story Points</th>
            <th id="assignee">Assignee</th>
            <th id="actions">Actions</th>
        </tr>
    </thead>
    <tbody>
    <#list tasks as task>
    <tr>
            <td class="id"><a href="/task?id=${task.id}">${task.id?keep_before("-")}</a></td>
            <td class="name"><span class="tag ${task.type?lower_case}">${task.type.getName()}</span></td>
            <td class="title">${task.title}</td>
            <td class="description"><#if task.description??>${task.description}</#if></td>
            <td class="title">${task.status.getName()}</td>
            <td class="story-points"><#if task.storyPoints??><span>${task.storyPoints}</span></#if></td>
            <td class="assigne"><#if task.assignee??><span>${task.assignee.fullName}</span></#if></td>
            <td class="actions">
                <a class="delete" data-id="${task.id}" href="#">DELETE</a>
                <a class="edit" data-id="${task.id}" href="#">EDIT</a>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</#macro>
<@display_page/>
<script>
        const deleteButtons = document.querySelectorAll('.delete');
        const editButtons = document.querySelectorAll('.edit');
        const handleDelete = async (e) => {
            e.preventDefault();
            const id = e.target.dataset.id;
            await deleteTask(id);
            location.reload()
        };
        const handleEdit = (e) => {
            e.preventDefault();
            const id = e.target.dataset.id;
            const path = 'task-edit?id=' + id;
            goTo(path)
        };
        deleteButtons.forEach(button => button.addEventListener('click', handleDelete));
        editButtons.forEach(button => button.addEventListener('click', handleEdit));
</script>

