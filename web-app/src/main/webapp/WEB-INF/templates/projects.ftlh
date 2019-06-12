<#include "common/base.ftl">
<#macro title>
   <h2>Backlog</h2>
</#macro>
<#macro page_content>
<table class="aui">
    <thead>
        <tr>
            <th id="id">id</th>
            <th id="name">Type</th>
            <th id="title">Title</th>
            <th id="description">Description</th>
            <th id="name">State</th>
            <th id="story-points">Story Points</th>
            <th id="actions">Actions</th>
        </tr>
    </thead>
    <tbody>
    <#list projects as project>
    <tr>
            <td class="id"><a href="/project?id=${project.id}">${project.id?keep_before("-")}</a></td>
            <td class="name"><span class="tag ${project.type?lower_case}">${project.type.getName()}</span></td>
            <td class="title">${project.title}</td>
            <td class="description"><#if project.description??>${project.description}</#if></td>
            <td class="title">${project.state.getName()}</td>
            <td class="story-points"><#if project.storyPoints??><span>${project.storyPoints}</span></#if></td>
            <td class="actions">
                <a class="delete" data-id="${project.id}" href="#">DELETE</a>
                <a class="edit" data-id="${project.id}" href="#">EDIT</a>
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
        const handleDelete = (e) => {
            e.preventDefault();
            const id = e.target.dataset.id;
            deleteProject(id);
            location.reload()
        };
        const handleEdit = (e) => {
            e.preventDefault();
            const id = e.target.dataset.id;
            const path = 'project-edit?id=' + id;
            goTo(path)
        };
        deleteButtons.forEach(button => button.addEventListener('click', handleDelete));
        editButtons.forEach(button => button.addEventListener('click', handleEdit));

</script>

