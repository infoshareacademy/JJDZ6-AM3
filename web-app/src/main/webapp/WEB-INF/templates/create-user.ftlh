<#include "common/base.ftl">
<#macro title>
   <h2><#if user??>Edit User<#else>Add User</#if></h2>
</#macro>
<#macro page_content>
        <div class="create-user">
            <form class="aui" method="POST">
                <div class="field-group">
                    <label for="full-name">Full Name<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="text" id="full-name"
                           name="fullName" value="${user???then(user.fullName,"")}">
                    <div class="error"></div>
                </div>
                <div class="field-group">
                    <label for="email">Email<span class="aui-icon icon-required">required</span></label>
                    <input class="text" type="email" id="email"
                           name="email" value="${user???then(user.email,"")}">
                    <div class="error"></div>
                </div>
                <div class="field-group">
                    <label for="name">Role</label>
                    <select class="select" id="role" name="role">
                        <#list roles as role>
                            <option <#if user???then(user.role,"DEVELOPER") == role>selected</#if>
                                    value="${role}">${role.getName()}</option>
                        </#list>
                    </select>
                </div>
                <div class="buttons-container">
                    <div class="buttons">
                        <input class="aui-button aui-button-primary" type="button"
                               value="<#if user??>Update<#else>Create</#if>"
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
        const fullName = document.getElementById('full-name').value;
        const email = document.getElementById('email').value;
        const role = document.getElementById('role').value;
        try {
            if (!id) {
                const { data } = await createUser({ fullName, email, role });
                const path = 'user?id=' + data.id;
                goTo(path)
            } else {
                const { data } = await updateUser(id, { fullName, email, role });
                const path = 'user?id=' + data.id;
                goTo(path)
            }
        } catch (e) {
            console.error(e);
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
