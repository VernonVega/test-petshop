Ext.onReady(function () {

  Ext.define('evgkit.model.Chat', {
    extend: 'Ext.data.Model',
    fields: [
      {name: 'id', type: 'int'},
      {name: 'enabled', type: 'boolean'},
      {name: 'title', type: 'string'},
      {name: 'type', type: 'string'}
    ]
  });

  Ext.define('evgkit.store.Chats', {
    extend: 'Ext.data.Store',
    model: 'evgkit.model.Chat',
    fields: ['id', 'enabled', 'title', 'type'],
    proxy: {
      type: 'ajax',
      url: '/api/v1/chats',
      method: 'GET',
      reader: {
        type: 'json',
        root: 'chats'
      }
    },
    autoLoad: true
  });

  Ext.define('evgkit.view.ChatsList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.chatslist',
    title: 'Chats List',
    store: 'Chats',
    initComponent: function () {
      this.tbar = [{
        text: 'Add Chat',
        action: 'add',
        iconCls: 'chat-add'
      }];
      this.columns = [
        {
          header: 'ID',
          dataIndex: 'id',
          flex: 1
        },
        {
          header: 'Enabled',
          dataIndex: 'enabled',
          flex: 1,
          xtype: 'booleancolumn',
          trueText: 'Yes',
          falseText: 'No',
        },
        {
          header: 'Title',
          dataIndex: 'title',
          flex: 1
        },
        {
          header: 'Type',
          dataIndex: 'type',
          flex: 1
        },
        {
          header: 'Action', width: 50, renderer: function (v, m, r) {
            var id = Ext.id();
            var max = 15;
            Ext.defer(function () {
              Ext.widget('image', {
                renderTo: id,
                name: 'delete',
                src: 'images/chat_delete.png',
                listeners: {
                  afterrender: function (me) {
                    me.getEl().on('click', function () {
                      var grid = Ext.ComponentQuery.query('chatslist')[0];
                      if (grid) {
                        var sm = grid.getSelectionModel();
                        var rs = sm.getSelection();
                        if (!rs.length) {
                          Ext.Msg.alert('Info', 'No Chat Selected');
                          return;
                        }
                        Ext.Msg.confirm('Remove Chat',
                          'Are you sure you want to delete?',
                          function (button) {
                            if (button === 'yes') {
                              var chat = rs[0].getData();
                              Ext.Ajax.request({
                                url: '/api/v1/chats',
                                method: 'DELETE',
                                jsonData: chat,
                                success: function (response) {
                                  var grid = Ext.ComponentQuery.query('chatslist')[0];
                                  grid.getStore().load();
                                }
                              });
                            }
                          });
                      }
                    });
                  }
                }
              });
            }, 50);
            return Ext.String.format('<div id="{0}"></div>', id);
          }
        }
      ];
      this.callParent(arguments);
    }
  });

  Ext.define('evgkit.view.ChatsForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.chatsform',
    title: 'Add Chat',
    width: 350,
    layout: 'fit',
    resizable: false,
    closeAction: 'hide',
    modal: true,
    config: {
      recordIndex: 0,
      action: ''
    },
    items: [{
      xtype: 'form',
      layout: 'anchor',
      bodyStyle: {
        background: 'none',
        padding: '10px',
        border: '0'
      },
      defaults: {
        xtype: 'textfield',
        anchor: '100%'
      },
      items: [{
        name: 'id',
        fieldLabel: 'ID',
      }, {
        name: 'enabled',
        fieldLabel: 'Enabled',
        xtype: 'checkboxfield',
        inputValue: 'true',
        uncheckedValue: 'false'
      }, {
        name: 'title',
        fieldLabel: 'Title'
      }, {
        name: 'type',
        fieldLabel: 'Type'
      }]
    }],
    buttons: [{
      text: 'OK',
      action: 'add'
    }, {
      text: 'Reset',
      handler: function () {
        this.up('window').down('form').getForm().reset();
      }
    }, {
      text: 'Cancel',
      handler: function () {
        this.up('window').close();
      }
    }]
  });

  Ext.define('evgkit.controller.Chats', {
    extend: 'Ext.app.Controller',
    stores: ['Chats'],
    views: ['ChatsList', 'ChatsForm'],
    refs: [{
      ref: 'formWindow',
      xtype: 'chatsform',
      selector: 'chatsform',
      autoCreate: true
    }],
    init: function () {
      this.control({
        'chatslist > toolbar > button[action=add]': {
          click: this.showAddForm
        },
        'chatslist': {
          itemdblclick: this.onRowdblclick
        },
        'chatsform button[action=add]': {
          click: this.doAddChat
        }
      });
    },
    onRowdblclick: function (me, record, item, index) {
      var win = this.getFormWindow();
      win.setTitle('Edit Chat');
      win.setAction('edit');
      win.setRecordIndex(index);
      win.down('form').getForm().setValues(record.getData());
      win.show();
    },
    showAddForm: function () {
      var win = this.getFormWindow();
      win.setTitle('Add Chat');
      win.setAction('add');
      win.down('form').getForm().reset();
      win.show();
    },
    doAddChat: function () {
      var win = this.getFormWindow();
      var store = this.getChatsStore();
      var values = win.down('form').getValues();

      var action = win.getAction();
      var url = '/api/v1/chats';
      var method = 'POST';
      if (action === 'edit') {
        method = 'PUT';
      }
      Ext.Ajax.request({
        url: url,
        method: method,
        jsonData: values,
        success: function (response) {
          store.load();
        }
      });
      win.close();
    }
  });

  Ext.application({
    name: 'evgkit',
    controllers: ['Chats'],
    launch: function () {
      Ext.widget('chatslist', {
        width: 500,
        height: 300,
        renderTo: 'output'
      });
    }
  });
});