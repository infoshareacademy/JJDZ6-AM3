<#include "common/base.ftl">
<#macro title>
   <h2 class="page-title">Users</h2>
</#macro>
<#macro page_content>
<a class="aui-button aui-button-compact" href="/create-user" resolved="">Create User</a>
<table class="aui">
    <thead>
        <tr>
            <th id="id">#</th>
            <th id="fname">Full Name</th>
            <th id="email">Email</th>
            <th id="role">Role</th>
        </tr>
    </thead>
    <tbody>
    <#list users as user>
    <tr>
            <td class="id"><a href="/task?id=${user.id}">${user.id?keep_before("-")}</a></td>
            <td class="fname">${user.fullName}</td>
            <td class="emai">${user.email}</td>
            <td class="role">${user.role}</td>
            <td class="actions">
                <a class="delete" data-id="${user.id}" href="#">DELETE</a>
                <a class="edit" data-id="${user.id}" href="#">EDIT</a>
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
            deleteUser(id);
            location.reload()
        };
        const handleEdit = (e) => {
            e.preventDefault();
            const id = e.target.dataset.id;
            const path = 'user-edit?id=' + id;
            goTo(path)
        };
        deleteButtons.forEach(button => button.addEventListener('click', handleDelete));
        editButtons.forEach(button => button.addEventListener('click', handleEdit));
</script>

