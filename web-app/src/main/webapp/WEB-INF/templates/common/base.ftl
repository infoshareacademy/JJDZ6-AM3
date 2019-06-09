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
    <div id="page">
      <header id="header" role="banner">
        <nav class="aui-header aui-dropdown2-trigger-group" role="navigation">
          <div class="aui-header-inner">
            <div class="aui-header-primary">
              <h1 id="logo" class="aui-header-logo aui-header-logo-textonly"><a href="/"><span
                      class="aui-header-logo-device">SCRUM</span></a></h1>
              <ul class="aui-nav">
                <li><a class="aui-button aui-button-primary" href="/create-task">Create</a></li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
      <section id="content" role="main">
        <div class="aui-page-panel">
          <div class="aui-page-panel-inner">
            <div class="aui-page-panel-nav">
                <ul class="aui-nav">
                    <li class="aui-nav-selected">
                        <a class="aui-nav-item" href="/projects">
                            <!-- "Tier One" navigation items MUST have an AUI Icon before the text label -->
                            <span class="aui-icon aui-icon-small aui-iconfont-info"></span>
                            <!-- "Tier One" Nav items labels also need an extra SPAN around them with the aui-nav-item-label class -->
                            <span class="aui-nav-item-label">Projects</span>
                        </a>
                    </li>
                    <li>
                        <a class="aui-nav-item" href="/tasks" title="This is a really really long title for whatever
                        this is">
                            <span class="aui-icon aui-icon-small aui-iconfont-details"></span>
                            <span class="aui-nav-item-label">Issues</span>
                        </a>
                    </li>
                    <li>
                        <a class="aui-nav-item" href="/users">
                            <span class="aui-icon aui-icon-small aui-iconfont-jira-completed-task"></span>
                            <span class="aui-nav-item-label">Users</span>
                        </a>
                    </li>
                    <li>
                        <a class="aui-nav-item" href="/sprints">
                            <span class="aui-icon aui-icon-small aui-iconfont-time"></span>
                            <span class="aui-nav-item-label">Sprints</span>
                        </a>
                    </li>
                </ul>
            </div>
            <section class="aui-page-panel-content">
                <@title />
                <@page_content />
            </section>
          </div>
        </div>
      </section>
    </div>
    </body>

  </html>
</#macro>
