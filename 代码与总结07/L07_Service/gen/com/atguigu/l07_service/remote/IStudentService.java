/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\train\\150712\\workspace\\L07_Service\\src\\com\\atguigu\\l07_service\\remote\\IStudentService.aidl
 */
package com.atguigu.l07_service.remote;
public interface IStudentService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.atguigu.l07_service.remote.IStudentService
{
private static final java.lang.String DESCRIPTOR = "com.atguigu.l07_service.remote.IStudentService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.atguigu.l07_service.remote.IStudentService interface,
 * generating a proxy if needed.
 */
public static com.atguigu.l07_service.remote.IStudentService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.atguigu.l07_service.remote.IStudentService))) {
return ((com.atguigu.l07_service.remote.IStudentService)iin);
}
return new com.atguigu.l07_service.remote.IStudentService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getStudentById:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
com.atguigu.l07_service.remote.Student _result = this.getStudentById(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.atguigu.l07_service.remote.IStudentService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public com.atguigu.l07_service.remote.Student getStudentById(int id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.atguigu.l07_service.remote.Student _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(id);
mRemote.transact(Stub.TRANSACTION_getStudentById, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.atguigu.l07_service.remote.Student.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getStudentById = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public com.atguigu.l07_service.remote.Student getStudentById(int id) throws android.os.RemoteException;
}
