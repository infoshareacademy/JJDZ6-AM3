<#include "common/base.ftl">
<#macro title>
   <h2><#if project??>Edit Project<#else>Create Project</#if></h2>
</#macro>
<#macro page_content>
        <div class="create-project">
            <form class="aui" method="POST">
                <div class="field-group">
                    <label for="title">Name<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="text" id="name" name="name" value="${project???then(project.name,"")}">
                    <div class="error"></div>
                </div>
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
        const name = document.getElementById('name').value;
        try {
            if (!id) {
                const { data } = await createProject({ name });
                const path = 'project?id=' + data.id;
                goTo(path)
            } else {
                const { data } = await updateProject(id, { name });
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
