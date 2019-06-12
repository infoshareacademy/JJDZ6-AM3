<#include "common/base.ftl">
<#macro title>
   <h2><#if project??>Edit Project<#else>Create Project</#if></h2>
</#macro>
<#macro page_content>
        <div class="create-project">
            <form class="aui" method="POST">
                <div class="field-group">
                    <label for="title">Title<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="text" id="title" name="title" value="${project???then(project.title,"")}">
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
                    <label for="title">Product Owner<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="text" id="product-owner" name="product-owner" value="${project???then(project.owner,"")}">
                    <div class="error"></div>
                </div>
                <div class="field-group">
                    <label for="title">Developer<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="text" id="developer" name="developer" value="${project???then(project.developer,"")}">
                    <div class="error"></div>
                <div class="buttons-container">
                    <div class="buttons">
                        <input class="aui-button aui-button-primary" type="button"
                               value="<#if project??>Update<#else>Create</#if>"
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
        const productOwner = document.getElementById('product-owner').value;
        const developer = document.getElementById('developer').value;
        const projectId = document.getElementById('project').value;
        try {
            if (!id) {
                const { data } = await createProject({ title, productOwner, developer, projectId });
                const path = 'project?id=' + data.id;
                goTo(path)
            } else {
                const { data } = await updateProject(id, { title, productOwner, developer, projectId });
                const path = 'project?id=' + data.id;
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
