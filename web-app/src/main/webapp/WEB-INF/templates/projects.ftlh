<#include "common/base.ftl">
<#macro title>
   <h2>Backlog</h2>
</#macro>
<#macro page_content>
<table class="aui">
    <thead>
        <tr>
            <th id="id">id</th>
            <th id="name">Name</th>
            <th id="actions">Actions</th>
        </tr>
    </thead>
    <tbody>
    <#list projects as project>
    <tr>
            <td class="id"><a href="/project?id=${project.id}">${project.id?keep_before("-")}</a></td>
            <td class="name"><span>${project.name}</span></td>
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

