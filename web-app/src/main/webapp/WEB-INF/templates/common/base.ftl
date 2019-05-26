<#macro page_head>
  <#include "head.ftl">
</#macro>

<#macro page_content>
  <p>Basic Content</p>
</#macro>
<#macro title>
   <h2>Dashboard</h2>
</#macro>

<#macro display_page>
  <!doctype html>
  <html>

  <head>
    <@page_head />
  </head>

  <body>
    <body>
      <div id="page">
        <header id="header" role="banner">
          <nav class="aui-header aui-dropdown2-trigger-group" role="navigation">
            <div class="aui-header-inner">
              <div class="aui-header-primary">
                <h1 id="logo" class="aui-header-logo aui-header-logo-textonly"><a href="https://example.com/"><span
                      class="aui-header-logo-device">SCUM BOARD</span></a></h1>
                <ul class="aui-nav">
                  <li><a href="/projects">Projects</a></li>
                  <li><a href="/tasks">Backlog</a></li>
                  <li><a class="aui-button aui-button-primary" href="/create-task">Create</a></li>
                </ul>
              </div>
            </div>
          </nav>
        </header>
        <section id="content" role="main">
          <div class="aui-page-panel">
            <div class="aui-page-panel-inner">
              <section class="aui-page-panel-content">
                <@title />
                <@page_content />
              </section>
            </div>
          </div>
        </section>
      </div>
    </body>
  </body>

  </html>
</#macro>
